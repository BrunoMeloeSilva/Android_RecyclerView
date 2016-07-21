package bms.com.br.recyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(MainActivity.this);
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(llm);

        ArrayList<String> demoDados = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            demoDados.add("A"+i);
        }

        recyclerView.setAdapter(new RecyclerViewAdapter(demoDados));

    }

    public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewAux> {

        private List<String> items;

        //obtencao dos dados para a listagem
        RecyclerViewAdapter(List<String> lstStrings) {
            if (lstStrings == null) {
                throw new IllegalArgumentException("Não há elementos !");
            }
            items = lstStrings;
        }

        //infla a view que representa os itens de uma linha
        @Override
        public ViewAux onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
            return new ViewAux(itemView);
        }

        //alimenta a view que representa os itens de uma linha
        @Override
        public void onBindViewHolder(ViewAux viewHolder, int position) {
            String nome = items.get(position);
            viewHolder.name.setText(String.valueOf(nome));
        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        //design pattner obrigatorio a ser seguido
        public final class ViewAux extends RecyclerView.ViewHolder {
            TextView name;

            public ViewAux(View itemView) {
                super(itemView);
                name = (TextView) itemView.findViewById(R.id.textViewItem);
            }
        }
    }

}
