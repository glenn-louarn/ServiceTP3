package modele.algoRecherche;

import java.util.List;
import java.util.Optional;

import javax.ws.rs.client.Client;

import infrastructure.jaxrs.HyperLien;
import infrastructure.jaxrs.Outils;
import modele.Bibliotheque;
import modele.ImplemNomAlgorithme;
import modele.Livre;
import modele.NomAlgorithme;
import modele.RechercheAsynchroneAbstraite;

public class RechercheAsynchroneStreamParallele  extends RechercheAsynchroneAbstraite{

	private NomAlgorithme nomAlgorithme ;

	@Override
	public Optional<HyperLien<Livre>> chercher(Livre l, List<HyperLien<Bibliotheque>> bibliotheques, Client client) {
		return bibliotheques.parallelStream()
				.map((h) -> rechercheAsync(h, l, client))
				.map(Outils::remplirPromesse)
				.filter((x) -> x.isEmpty())
				.findAny()
				.orElse(Optional.empty());
	}
	
	@Override
	public NomAlgorithme nom() {
		// TODO Auto-generated method stub
		return null;
	}

	public RechercheAsynchroneStreamParallele(String nomAlgorithme) {
		super();
		this.nomAlgorithme = new ImplemNomAlgorithme(nomAlgorithme);
	}

	
}