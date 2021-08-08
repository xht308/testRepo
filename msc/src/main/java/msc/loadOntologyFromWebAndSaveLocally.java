package msc;

import java.io.File;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLException;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;

public class loadOntologyFromWebAndSaveLocally {

	public static void main(String[] args) throws OWLException {
		OWLOntologyManager man = OWLManager.createOWLOntologyManager();
		File owlFile = new File("D:\\family.rdf.owl");
		OWLOntology o2 = man.loadOntologyFromOntologyDocument(owlFile);
		System.out.println("Axioms: "+o2.getAxiomCount()+", Format: "+man.getOntologyFormat(o2));

	}

}
