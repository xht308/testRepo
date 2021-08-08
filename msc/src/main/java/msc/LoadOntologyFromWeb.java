package msc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.formats.FunctionalSyntaxDocumentFormat;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;

public class LoadOntologyFromWeb {

	public static void main(String[] args) {
		OWLOntologyManager man = OWLManager.createOWLOntologyManager();
		IRI webOntology = IRI.create("http://protege.stanford.edu/ontologies/pizza/pizza.owl");
		OWLOntology o;
		try {
			o = man.loadOntologyFromOntologyDocument(webOntology);
			System.out.println(o);
			File outputFile = new File("D:\\OutputOntology.fnuc.owl");
			try {
				man.saveOntology(o, new FunctionalSyntaxDocumentFormat(), new FileOutputStream(outputFile));
			} catch (OWLOntologyStorageException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (OWLOntologyCreationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
