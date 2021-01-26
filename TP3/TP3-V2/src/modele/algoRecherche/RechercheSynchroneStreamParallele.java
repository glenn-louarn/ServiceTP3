package modele.algoRecherche;

import java.util.List;
import java.util.Optional;

import javax.ws.rs.client.Client;

import infrastructure.jaxrs.HyperLien;
import modele.Bibliotheque;
import modele.ImplemNomAlgorithme;
import modele.Livre;
import modele.NomAlgorithme;
import modele.RechercheSynchroneAbstraite;

public class RechercheSynchroneStreamParallele extends RechercheSynchroneAbstraite {

	private NomAlgorithme nomAlgorithme ;
	@Override
	public Optional<HyperLien<Livre>> chercher(Livre l, List<HyperLien<Bibliotheque>> bibliotheques, Client client) {
		// TODO Auto-generated method stub
		Optional<HyperLien<Livre>> res = bibliotheques.parallelStream()
				.map(h -> this.rechercheSync(h, l, client)).filter(h -> h.isPresent()).findAny().orElse( Optional.empty());
		return res;
	}

	@Override
	public NomAlgorithme nom() {
		// TODO Auto-generated method stub
		return nomAlgorithme;
	}

	public RechercheSynchroneStreamParallele(String nomAlgorithme) {
		super();
		this.nomAlgorithme = new ImplemNomAlgorithme(nomAlgorithme);
	}
	

}
