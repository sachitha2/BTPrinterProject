/***
 * Sachitha hirushan premarathna
 *
 *
 */

package com.example.chata.btprinterproject;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private ListView lstvw;
    private Object mac;
    private ArrayAdapter aAdapter;
    private BluetoothAdapter bAdapter = BluetoothAdapter.getDefaultAdapter();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Button btn = (Button)findViewById(R.id.btnGet);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bAdapter==null){
                    Toast.makeText(getApplicationContext(),"Bluetooth Not Supported",Toast.LENGTH_SHORT).show();
                }
                else{
                    final Set<BluetoothDevice> pairedDevices = bAdapter.getBondedDevices();
                    ArrayList list = new ArrayList();
                    final ArrayList bt = new ArrayList();
                    if(pairedDevices.size()>0){
                        int i = 0;
                        for(BluetoothDevice device: pairedDevices){
                            String devicename = device.getName();
                            String macAddress = device.getAddress();
                            list.add("Name: "+devicename+"MAC Address: "+macAddress);
                            bt.add(i,macAddress);
                            i++;
                        }
                        lstvw = (ListView) findViewById(R.id.deviceList);
                        aAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, list);
                        lstvw.setAdapter(aAdapter);

                        lstvw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                if(0 == 0){
                                      Toast.makeText(MainActivity.this, "clicked"+ bt.get(position), Toast.LENGTH_SHORT).show();
//                                    Intent myIntent = new Intent(view.getContext(),ConnectToPrinter.class);
////                                    startActivityForResult(myIntent,bt.get(position));
                                    mac = bt.get(position);
                                    Intent intent =new  Intent(MainActivity.this,ConnectToPrinter.class);
                                    intent.putExtra("MAC", String.valueOf(mac) );
                                    intent.putExtra("email","chatson@chatson.com");

                                    startActivity(intent);
                                }
                            }
                        });
                    }
                }
            }
        });

    }
}
