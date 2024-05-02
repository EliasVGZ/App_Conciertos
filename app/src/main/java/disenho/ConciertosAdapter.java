package disenho;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto_eventos.R;

import java.util.ArrayList;

import modelos.Conciertos;

public class ConciertosAdapter extends RecyclerView.Adapter<ConciertosAdapter.ConciertosViewHolder> {
    private ArrayList<Conciertos> listaConciertos;
    private Context context;

    public ConciertosAdapter(Context context, ArrayList<Conciertos> listaConciertos) {
        this.context = context;
        this.listaConciertos = listaConciertos;
    }

    @NonNull
    @Override
    public ConciertosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_personalizado_foot, parent, false);
        return new ConciertosViewHolder(view);
    }

    //para cada item de la lista se le asigna un concierto
    @Override
    public void onBindViewHolder(@NonNull ConciertosViewHolder holder, int position) {
        Conciertos concierto = listaConciertos.get(position);

        // Configura la imagen del concierto
        int imagenId = obtenerIdImagen(concierto.getImagen());
        holder.iv_concierto.setImageResource(imagenId);

        // Configura la información del concierto
        holder.tv_nombreConcierto.setText(concierto.getNombreConciertos());
        holder.tv_lugarConcierto.setText(concierto.getLugar());
        holder.tv_fechaConcierto.setText(concierto.getFecha());
        holder.tv_generoConcierto.setText(concierto.getGenero());
        holder.tv_precioConcierto.setText(concierto.getPrecio());
    }



    @Override//metodo que devuelve el numero de elementos que tiene la lista
    public int getItemCount() {
        return listaConciertos.size();
    }

    // Clase interna, que extiende de RecyclerView.ViewHolder para poder acceder a los elementos de la vista
    public class ConciertosViewHolder extends RecyclerView.ViewHolder {
        TextView tv_nombreConcierto, tv_ciudad, tv_lugarConcierto, tv_fechaConcierto, tv_generoConcierto, tv_precioConcierto;
        ImageView iv_concierto;

        public ConciertosViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_nombreConcierto = itemView.findViewById(R.id.tv_nombreConcierto);
            //tv_ciudad = itemView.findViewById(R.id.tv_ciudad);
            tv_lugarConcierto = itemView.findViewById(R.id.tv_lugarConcierto);
            tv_fechaConcierto = itemView.findViewById(R.id.tv_fechaConcierto);
            tv_generoConcierto = itemView.findViewById(R.id.tv_generoConcierto);
            tv_precioConcierto = itemView.findViewById(R.id.tv_precioConcierto);
            iv_concierto = itemView.findViewById(R.id.iv_concierto);

            // ancho de la pantalla para que cada item se meta en una sola fila
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((Activity) itemView.getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int screenWidth = displayMetrics.widthPixels;

            // Establece el ancho de itemView (que es la CardView) al ancho de la pantalla
            ViewGroup.LayoutParams layoutParams = itemView.getLayoutParams();
            layoutParams.width = screenWidth;
            itemView.setLayoutParams(layoutParams);
        }
    }

    public int obtenerIdImagen(int idImagen) {
        return context.getResources().getIdentifier("concierto_" + idImagen, "drawable", context.getPackageName());
    }
}