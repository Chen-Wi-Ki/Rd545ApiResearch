package wi.ki.rd545;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import jp.co.tanita.comm.ble.TNTBLEManager;
import jp.co.tanita.comm.ble.TNTBLEPeripheral;
import jp.co.tanita.comm.ble.TNTDeviceInformation;
import jp.co.tanita.comm.ble.TNTDeviceType;
import jp.co.tanita.comm.ble.TNTMeasurementInformation;
import jp.co.tanita.comm.ble.TNTUserInformation;

public class MainActivity extends AppCompatActivity {
    TextView EventText;
    Button Event1Btn;
    TNTBLEManager tntbleManager;
    TNTBLEPeripheral _tntblePeripheral;
    TNTBLEManager.TNTBLEManagerListener tntbleManagerListener;
    TNTBLEPeripheral.TNTBLEPeripheralListener _TNTBLEPeripheralListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        EventText=findViewById(R.id.EventMag);
        Event1Btn=findViewById(R.id.Event1Btn);
    }

    public void OnClick1(View v)
    {
        Log.i("wiki1","API執行...");
        //https://aware-gold-229.notion.site/TANITA-API-Overview-14200962af9c8068909cfba328ef7953
        //TANITA API Overview
        //一、初始化 TNTBLEManager
        tntbleManager = new TNTBLEManager(this);
        //二、設置 TNTBLEManager 監聽器
        tntbleManagerListener = new TNTBLEManager.TNTBLEManagerListener() {
            @Override
            public void onTNTBLEManagerStateUpdated(int i) {
                //三、配對連線 TANITA 設備
                Log.i("wiki1","API進入預期的配對連線...");
                int state = tntbleManager.getState();
                if (TNTBLEManager.TNTBLEManagerState.READY == state) {
                    // 連線體脂機 type 為 BODY_COMPOSITION_MONITOR
                    tntbleManager.connect(TNTDeviceType.BODY_COMPOSITION_MONITOR);
                    //連線成功時會觸發 TNTBLEManagerListener::onTNTBLEPeripheralConnected()。
                }
            }

            @Override
            public void onTNTBLEPeripheralConnected(TNTBLEManager tntbleManager, TNTBLEPeripheral tntblePeripheral, TNTDeviceInformation tntDeviceInformation) {
                //依照需求可以儲存已經連上線的設備以及設置設備的事件監聽。
                Log.i("wiki1","API進入預期的監聽事件...");
                if (tntblePeripheral != null) {
                    // 保存完成連線的 Peripheral
                    _tntblePeripheral = tntblePeripheral;

                    // 設置 Peripheral 監聽器
                    _TNTBLEPeripheralListener = new TNTBLEPeripheral.TNTBLEPeripheralListener() {
                        @Override
                        public void onDisconnectionRequested(TNTBLEPeripheral tntblePeripheral, int i, int i1) {

                        }

                        @Override
                        public void onUUIDSaved(TNTBLEPeripheral tntblePeripheral, int i, int i1, int i2) {

                        }

                        @Override
                        public void onUserInformationRetrieved(TNTBLEPeripheral tntblePeripheral, TNTUserInformation tntUserInformation, int i, int i1) {

                        }

                        @Override
                        public void onUserInformationSaved(TNTBLEPeripheral tntblePeripheral, TNTUserInformation tntUserInformation, int i, int i1) {

                        }

                        @Override
                        public void onMeasurementFinished(TNTBLEPeripheral tntblePeripheral, int i, int i1) {

                        }

                        @Override
                        public void onMeasurementCountRetrieved(TNTBLEPeripheral tntblePeripheral, int i, int i1, int i2) {

                        }

                        @Override
                        public void onMeasurementInformationRetrieved(TNTBLEPeripheral tntblePeripheral, int i, TNTMeasurementInformation tntMeasurementInformation, int i1, int i2) {

                        }

                        @Override
                        public void onRssiUpdated(TNTBLEPeripheral tntblePeripheral, int i) {

                        }

                        @Override
                        public void onRequestFailed(TNTBLEPeripheral tntblePeripheral, int i) {

                        }
                    };
                    _tntblePeripheral.setTNTBLEPeripheralListener(_TNTBLEPeripheralListener);

                }
            }

            @Override
            public void onTNTBLEPeripheralDisconnected(TNTBLEManager tntbleManager, TNTBLEPeripheral tntblePeripheral, int i) {

            }

            @Override
            public void onTNTBLEPeripheralConnectionFailed(TNTBLEManager tntbleManager, TNTBLEPeripheral tntblePeripheral, int i) {

            }

            @Override
            public void onTNTBLEPeripheralConnectionIgnored(TNTBLEManager tntbleManager, int i) {

            }
        };
        // 設置監聽器
        tntbleManager.setTNTBLEManagerListener(tntbleManagerListener);

        EventText.setText(EventText.getText()+"API執行...\n");
    }
}