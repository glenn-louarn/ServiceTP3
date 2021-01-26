package modele.algoRecherche;

import java.util.List;
import java.util.Optional;

import javax.ws.rs.client.Client;

import infrastructure.jaxrs.HyperLien;
import modele.Bibliotheque;
import modele.ImplemNomAlgorithme;
import modele.Livre;
import modele.NomAlgorithme;
import modele.RechercheAsynchroneAbstraite;

public class RechercheAsynchroneStreamRx  extends RechercheAsynchroneAbstraite{

	private NomAlgorithme nomAlgorithme ;

	@Override
	public Optional<HyperLien<Livre>> chercher(Livre l, List<HyperLien<Bibliotheque>> bibliotheques, Client client) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public NomAlgorithme nom() {
		// TODO Auto-generated method stub
		return nomAlgorithme;
	}

	public RechercheAsynchroneStreamRx(String nomAlgorithme) {
		super();
		this.nomAlgorithme = new ImplemNomAlgorithme(nomAlgorithme);
	}
	
	
}
