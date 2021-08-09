package msc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.formats.FunctionalSyntaxDocumentFormat;
import org.semanticweb.owlapi.model.*;

public class LoadAndIterateOntologyFromWeb {

	public static void main(String[] args) throws OWLException, FileNotFoundException {
		OWLOntologyManager man = OWLManager.createOWLOntologyManager();
		IRI webOntology = IRI.create("http://protege.stanford.edu/ontologies/pizza/pizza.owl");
		OWLOntology o;
		o = man.loadOntologyFromOntologyDocument(webOntology);
		System.out.println(o);
		o.signature().filter(e->owlClassNameStartsWithP(e)).forEach(System.out::println);

	}
	
	private static boolean owlClassNameStartsWithStr(OWLEntity e, String str) {
		return !e.isBuiltIn()&&e.getIRI().getRemainder().orElse("").startsWith(str);
	}
	
	private static boolean owlClassNameStartsWithP(OWLEntity e) {
		return !e.isBuiltIn()&&e.getIRI().getRemainder().orElse("").startsWith("P");
	}

}
