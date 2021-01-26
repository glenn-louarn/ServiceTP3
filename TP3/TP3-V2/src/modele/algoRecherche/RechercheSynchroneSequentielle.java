package modele.algoRecherche;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.ws.rs.client.Client;

import infrastructure.jaxrs.HyperLien;
import modele.Bibliotheque;
import modele.ImplemNomAlgorithme;
import modele.Livre;
import modele.NomAlgorithme;
import modele.RechercheSynchroneAbstraite;

public class RechercheSynchroneSequentielle extends RechercheSynchroneAbstraite{

	private NomAlgorithme nomAlgorithme ;
	@Override
	public Optional<HyperLien<Livre>> chercher(Livre l, List<HyperLien<Bibliotheque>> bibliotheques, Client client) {
		// TODO Auto-generated method stub
		for(HyperLien<Bibliotheque> h: bibliotheques) {
			Optional<HyperLien<Livre>> res = this.rechercheSync(h, l, client);
			if(!res.isEmpty()) {
				return res;
			}
		}
		return null;
	}

	@Override
	public NomAlgorithme nom() {
		// TODO Auto-generated method stub
		return nomAlgorithme;
	}

	public RechercheSynchroneSequentielle(String nomAlgorithme) {
		super();
		this.nomAlgorithme = new ImplemNomAlgorithme(nomAlgorithme);
	}
	
	


}
