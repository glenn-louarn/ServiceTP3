package modele.algoRecherche;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicReference;

import javax.ws.rs.client.Client;

import infrastructure.jaxrs.HyperLien;
import modele.Bibliotheque;
import modele.ImplemNomAlgorithme;
import modele.Livre;
import modele.NomAlgorithme;
import modele.RechercheSynchroneAbstraite;

public class RechercheSynchroneMultiTaches extends RechercheSynchroneAbstraite{

	private NomAlgorithme nomAlgorithme ;
	private ExecutorService execService ;
	
	@Override
	public Optional<HyperLien<Livre>> chercher(Livre l, List<HyperLien<Bibliotheque>> bibliotheques, Client client) {
		Iterator<HyperLien<Bibliotheque>> it =bibliotheques.iterator();
		CountDownLatch verrou = new CountDownLatch(1);
		AtomicReference<Optional<HyperLien<Livre>>> res= new AtomicReference<>(Optional.empty());
		for(HyperLien<Bibliotheque> h: bibliotheques) {
			execService.submit(() -> {
				Optional<HyperLien<Livre>> tempo = this.rechercheSync(h, l, client);
				if(tempo.isEmpty()) {
					verrou.countDown();
				}else {
					liberer(verrou);
					res.set(tempo);
				}
			});
		}
		try {
			verrou.await();
			
		}catch(InterruptedException ex){
		}
		return res.get();
	}
	private static synchronized void liberer(CountDownLatch verrou) {
		for(int i=0;i<verrou.getCount();i++) {
			verrou.countDown();
		}
	}

	@Override
	public NomAlgorithme nom() {
		return this.nomAlgorithme;
	}

	public RechercheSynchroneMultiTaches(String nomAlgorithme) {
		super();
		this.nomAlgorithme = new ImplemNomAlgorithme(nomAlgorithme);
		this.execService = Executors.newCachedThreadPool();
	}

}
