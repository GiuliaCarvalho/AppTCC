package br.com.giulia.webservicetcc;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import br.com.giulia.webservicetcc.webservices.CarregaListaServicosListener;
import br.com.giulia.webservicetcc.webservices.WebServiceControle;
import br.com.giulia.webservicetcc.webservices.content.AppSquidexInfo;
import br.com.giulia.webservicetcc.webservices.content.Item;

public class ServicoActivity extends AppCompatActivity {

    private ListView lvServicos;
    private SwipeRefreshLayout srServicos;
    private BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicos);
        //
        inicializaComponentes();
        //
        carregaListaServicos();
    }

    private void inicializaComponentes() {
        navigation = findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.menuServicos);
        lvServicos = findViewById(R.id.lvServicos);
        srServicos = findViewById(R.id.srServicos);
        //
        srServicos.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                carregaListaServicos();
            }
        });
        //
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menuServicos:

                        return true;
                    case R.id.menuEmergencia:
                        startActivity(new Intent(ServicoActivity.this, EmergenciaActivity.class));
                        ServicoActivity.this.finish();
                        return true;
                    case R.id.menuRoteiro:
                        startActivity(new Intent(ServicoActivity.this, RoteiroActivity.class));
                        ServicoActivity.this.finish();
                        return true;
                }
                return false;
            }
        });
    }

    private void carregaListaServicos() {
        srServicos.setRefreshing(true);
        new WebServiceControle().carregaListaServicos(this, new CarregaListaServicosListener() {
            @Override
            public void onErro() {
                srServicos.setRefreshing(false);
            }

            @Override
            public void onSucesso(AppSquidexInfo appSquidexInfo) {
                srServicos.setRefreshing(false);
                appSquidexInfo.getItems();
                lvServicos.setAdapter(new ArrayAdapter<Item>(ServicoActivity.this, android.R.layout.simple_list_item_1, appSquidexInfo.getItems()));
            }
        });
    }

}
