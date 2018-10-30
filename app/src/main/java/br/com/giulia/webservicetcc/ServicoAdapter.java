package br.com.giulia.webservicetcc;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

import br.com.giulia.webservicetcc.webservices.content.Item;

public class ServicoAdapter extends ArrayAdapter<Item> {

    private int resource;
    private List<Item> list;

    public ServicoAdapter(@NonNull Context context, int resource,
                            List<Item> list)
    {
        super(context, resource, list);
        this.resource = resource;
        this.list = list;
    }

  /*  @NonNull
    @Override
    public View getView(int position, @Nullable View convertView,
                        @NonNull ViewGroup parent)
    {
        if(convertView == null)
            convertView = criaView(parent);

        Holder holder = (Holder) convertView.getTag();

        Item item = getItem(position);
        holder.t
    } */

}
