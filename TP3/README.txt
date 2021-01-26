Groupe de de Isuru Haupe, Christophe Dos Santos Neto et Glenn Louarn
Vous trouverez tous nos algorithme de recherche dans le dossier TP3-V2/src/modele/algoRecherche

Q? En étudiant le code des interfaces Bibliotheque et Portail (et de leurs interfaces parentes) ainsi que celui de leurs implémentations, déterminer l'ensemble des requêtes http acceptées par ces services. On supposera que la bibliothèque est déployée à l'adresse BIBLIO et le portail à l'adresse PORTAIL. Placer votre réponse dans un fichier readme.

- Repertoire {

	@PUT
	@Produces(TYPE_MEDIA)
	@Consumes(TYPE_MEDIA)
	@ReponsesPUTOption
	// Requête (méthode http + url) : PUT, http://localhost:8081/PortailServeur/bibliotheque/{id}
	// Corps : application/xml
	// Réponses (à spécifier par code) : (404) Optional<HyperLien<Livre>>, (201) HyperLien<Livre>
	// - code : 
	Optional<HyperLien<Livre>> chercher(Livre l);


	@PUT
	@ReponsesPUTOption
	@Path(JAXRS.SOUSCHEMIN_ASYNC)
	@Consumes(JAXRS.TYPE_MEDIA)
	@Produces(JAXRS.TYPE_MEDIA)
	// Requête (méthode http + url) : PUT, http://localhost:8081/PortailServeur/async/bibliotheque/{id}
	// Corps : application/xml
	// Réponses (à spécifier par code) : (404) Optional<HyperLien<Livre>>, (201) HyperLien<Livre>
	// - code : 
	Future<Optional<HyperLien<Livre>>> chercherAsynchrone(Livre l, @Suspended final AsyncResponse ar);

	@GET
	@Path(SOUSCHEMIN_CATALOGUE)
	@Produces(TYPE_MEDIA)
	// Requête (méthode http + url) : GET,  http://localhost:8081/PortailServeur/catalogue/bibliotheque/(id}
	// Corps : application/xml
	// Réponses (à spécifier par code) : (201) HyperLiens<Livre>
	// - code : 
	HyperLiens<Livre> repertorier();

- Archive 
	@Path("{id}")
	@ReponsesGETNullEn404
	// Adresse de la sous-ressource : id
	// Requête sur la sous-ressource (méthode http + url) : GET, http://localhost:8081/PortailServeur/bibliotheque/{id}
	// Corps :  application/xml
	// Réponses (à spécifier par code) : (201) Livre, (404) Null
	// - code : 
	Livre sousRessource(@PathParam("id") IdentifiantLivre id) ;

	@Path("{id}")
	@GET 
	@Produces(JAXRS.TYPE_MEDIA)
	@ReponsesGETNullEn404
	// Requête (méthode http + url) : GET, http://localhost:8081/PortailServeur/bibliotheque/{id}
	// Corps :  application/xml
	// Réponses (à spécifier par code) : (201) Livre, (404) Null
	// - code : 
	Livre getRepresentation(@PathParam("id") IdentifiantLivre id);

	@POST
	@ReponsesPOSTEnCreated
	@Consumes(JAXRS.TYPE_MEDIA)
	@Produces(JAXRS.TYPE_MEDIA)
	// Requête (méthode http + url) : POST, http://localhost:8081/PortailServeur/bibliotheque
	// Corps :  application/xml
	// Réponses (à spécifier par code) : (200) HyperLien<Livre>
	// - code : 
	HyperLien<Livre> ajouter(Livre l);
}

- AdminAlgo
	@PUT
	@Path(JAXRS.SOUSCHEMIN_ALGO_RECHERCHE)
	@Consumes(JAXRS.TYPE_MEDIA)
	// Requête (méthode http + url) : PUT, http://localhost:8081/PortailServeur/admin/recherche
	// Corps : application/xml
	// Réponses (à spécifier par code) : void
	// - code : 
	void changerAlgorithmeRecherche(NomAlgorithme algo);


Q? En étudiant les interfaces NomAlgorithme et Livre, donner le schéma et un exemple de données XML pour un nom d'algorithme et un livre. Répondre dans le readme.
- Schéma : NomAlgorithme 

     <xs:element name="algo" type="NomAlgorithme"/>
     <xs:complexType name="NomAlgorithme">
       <xs:sequence>
       </xs:sequence>
     </xs:complexType>

- Exemple : NomAlgorithme 
     <NomAlgorithme >
     </NomAlgorithme >

- Schéma : Livre 

     <xs:element name="livre" type="Livre"/>
     <xs:complexType name="Livre">
       <xs:sequence>
       </xs:sequence>
     </xs:complexType>
     </xs:complexType>