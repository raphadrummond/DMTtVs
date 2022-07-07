package databit.com.br.datamobile.pages;

import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.SupportMapFragment;

import databit.com.br.datamobile.R;
import databit.com.br.datamobile.domain.Os;
import databit.com.br.datamobile.fragment.HistoricoFragment;
import databit.com.br.datamobile.fragment.HistoricoTELFragment;
import databit.com.br.datamobile.fragment.HistoricoTIFragment;
import databit.com.br.datamobile.fragment.InforFragment;
import databit.com.br.datamobile.fragment.ItemFragment;
import databit.com.br.datamobile.fragment.LogFragment;
import databit.com.br.datamobile.fragment.OsDefeitoFragment;
import databit.com.br.datamobile.fragment.PendenteFragment;
import databit.com.br.datamobile.fragment.SerialFragment;
import databit.com.br.datamobile.fragment.TrocadaFragment;

/**
 * Created by developer on 23/04/16.
 */

public class PagerFragment extends Fragment {
    private ViewPagerAdapter adapter;
    private TabLayout tabLayout;
    public Os osselec;

    private SupportMapFragment mapFragment = new SupportMapFragment();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new ViewPagerAdapter(getFragmentManager());
        adapter.addFragment(new InforFragment(), "Infor");
        adapter.addFragment(mapFragment, "Mapa");

        switch (osselec.getOperacaomobile()) {
            case 1: {
                adapter.addFragment(new HistoricoFragment(), "Histórico");
                adapter.addFragment(new OsDefeitoFragment(), "Defeito");
                adapter.addFragment(new PendenteFragment(), "Pendente");
                adapter.addFragment(new TrocadaFragment(), "Trocada");
                break;
            }
            case 2: case 3: {
                adapter.addFragment(new ItemFragment(), "Itens");
                adapter.addFragment(new SerialFragment(), "Seriais");
                break;
            }
            case 4: case 5: case 6:{
                adapter.addFragment(new HistoricoTIFragment(), "Histórico");
                break;
            }
            case 7: {
                adapter.addFragment(new HistoricoTELFragment(), "Atendimentos");
                break;
            }
        }
        adapter.addFragment(new LogFragment(), "Log");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pager, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);

        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
    }

    public SupportMapFragment getMapFragment() {
        return mapFragment;
    }

    public void setMapFragment(SupportMapFragment mapFragment) {
        this.mapFragment = mapFragment;
    }
}
