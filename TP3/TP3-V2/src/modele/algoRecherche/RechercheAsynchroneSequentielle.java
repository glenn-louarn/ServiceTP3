package modele.algoRecherche;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.ws.rs.client.Client;

import infrastructure.jaxrs.HyperLien;
import modele.Bibliotheque;
import modele.ImplemNomAlgorithme;
import modele.Livre;
import modele.NomAlgorithme;
import modele.RechercheAsynchroneAbstraite;

public class RechercheAsynchroneSequentielle  extends RechercheAsynchroneAbstraite{

	private NomAlgorithme nomAlgorithme ;
	@Override
	public Optional<HyperLien<Livre>> chercher(Livre l, List<HyperLien<Bibliotheque>> bibliotheques, Client client) {
		List<Future<Optional<HyperLien<Livre>>>> promesses = new ArrayList<>();
		for (HyperLien<Bibliotheque> h : bibliotheques) {
			promesses.add(rechercheAsync(h, l, client));
		}
		Optional<HyperLien<Livre>> buffer;
		try {
			for (Future<Optional<HyperLien<Livre>>> promesse : promesses) {
				buffer = promesse.get();
				if (buffer.isPresent()) return buffer;
			}
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}
	
	@Override
	public NomAlgorithme nom() {
		// TODO Auto-generated method stub
		return null;
	}

	public RechercheAsynchroneSequentielle(String nomAlgorithme) {
		super();
		this.nomAlgorithme = new ImplemNomAlgorithme(nomAlgorithme);
	}
	
}