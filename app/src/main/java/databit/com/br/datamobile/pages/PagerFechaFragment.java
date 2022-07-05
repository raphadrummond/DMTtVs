package databit.com.br.datamobile.pages;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import databit.com.br.datamobile.R;
import databit.com.br.datamobile.dao.ConfigFTPDAO;
import databit.com.br.datamobile.dao.ConfigmobileDAO;
import databit.com.br.datamobile.dao.ConfiguracaoDAO;
import databit.com.br.datamobile.domain.ConfigFTP;
import databit.com.br.datamobile.domain.Configmobile;
import databit.com.br.datamobile.domain.Configuracao;
import databit.com.br.datamobile.domain.Os;
import databit.com.br.datamobile.fragment.ArquivoFragment;
import databit.com.br.datamobile.fragment.AtendimentoFragment;
import databit.com.br.datamobile.fragment.CustoFragment;
import databit.com.br.datamobile.fragment.DefeitoFragment;
import databit.com.br.datamobile.fragment.EntregaFragment;
import databit.com.br.datamobile.fragment.InforFechaFragment;
import databit.com.br.datamobile.fragment.LaudoFragment;
import databit.com.br.datamobile.fragment.ModuloFragment;
import databit.com.br.datamobile.fragment.ObsFragment;
import databit.com.br.datamobile.fragment.ObsPendenteFragment;
import databit.com.br.datamobile.fragment.ObsTrocadaFragment;
import databit.com.br.datamobile.fragment.ObsentregaFragment;
import databit.com.br.datamobile.fragment.ParticipanteFragment;
import databit.com.br.datamobile.fragment.PendenteOSFragment;
import databit.com.br.datamobile.fragment.PrevisaoFragment;
import databit.com.br.datamobile.fragment.RecolhaFragment;
import databit.com.br.datamobile.fragment.TrocadaOSFragment;


public class PagerFechaFragment extends Fragment {
    private ViewPagerAdapter adapter;
    private TabLayout tabLayout;
    public Os osselec;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ConfiguracaoDAO configuracaoDAO = new ConfiguracaoDAO();
        Configuracao configuracao = configuracaoDAO.procuraConfiguracao("id = 1");

        ConfigFTPDAO configFTPDAO = new ConfigFTPDAO();
        ConfigFTP configFTP = configFTPDAO.procuraFtp("id = 1");

        ConfigmobileDAO configmobileDAO = new ConfigmobileDAO();
        Configmobile configmobile = configmobileDAO.procuraConfigmobile("id = "+osselec.getOperacaomobile().toString());

        adapter = new ViewPagerAdapter(getFragmentManager());
        switch (osselec.getOperacaomobile()) {
            case 1: {
                if (!(osselec.getPreventiva().equals("A"))) {
                    adapter.addFragment(new InforFechaFragment(), "Informações");
                    adapter.addFragment(new ObsFragment(), "Laudo");
                    if (configuracao.getTipolan().toString().equals("M")) {
                        adapter.addFragment(new ObsPendenteFragment(), "Pendente");
                        adapter.addFragment(new ObsTrocadaFragment(), "Trocada");
                    } else {
                        adapter.addFragment(new PendenteOSFragment(), "Pendente");
                        adapter.addFragment(new TrocadaOSFragment(), "Trocada");
                        adapter.addFragment(new RecolhaFragment(), "Recolha");
                        adapter.addFragment(new CustoFragment(), "Custos");
                    }
                }
                else {
                    adapter.addFragment(new InforFechaFragment(), "Medidores");
                    adapter.addFragment(new ObsFragment(), "Observações");
                }
                break;
            }
            case 2: case 3:{
                adapter.addFragment(new EntregaFragment(), "Informações");
                adapter.addFragment(new ObsentregaFragment(), "Observações");
                break;
            }
            case 4: case 6:{
                adapter.addFragment(new LaudoFragment(), "Laudo Técnico");
                adapter.addFragment(new DefeitoFragment(), "Defeito");
                break;
            }
            case 5: {
                adapter.addFragment(new LaudoFragment(), "Laudo Técnico");
                adapter.addFragment(new ModuloFragment(), "Módulos");
                adapter.addFragment(new ParticipanteFragment(), "Participantes");
                break;
            }
            case 7: {
                adapter.addFragment(new AtendimentoFragment(), "Informações");
                adapter.addFragment(new PrevisaoFragment(), "Retorno");
                break;
            }
        }
        if ((configFTP.getEndereco() != null) && (configmobile.getPastaftp() != null)) {
            adapter.addFragment(new ArquivoFragment(), "Arquivos");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pager_fecha, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewPagerfecha);
        viewPager.setAdapter(adapter);

        tabLayout = (TabLayout) view.findViewById(R.id.tabLayoutfecha);
        tabLayout.setupWithViewPager(viewPager);
    }


}
