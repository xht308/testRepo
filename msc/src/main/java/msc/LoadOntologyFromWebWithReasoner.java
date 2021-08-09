package msc;

import org.semanticweb.HermiT.ReasonerFactory;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.formats.FunctionalSyntaxDocumentFormat;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.reasoner.*;

public class LoadOntologyFromWebWithReasoner {

	public static void main(String[] args) throws OWLException {
		OWLOntologyManager man = OWLManager.createOWLOntologyManager();
		IRI webOntology = IRI.create("http://protege.stanford.edu/ontologies/pizza/pizza.owl");
		OWLOntology o;
		o = man.loadOntologyFromOntologyDocument(webOntology);
		OWLDataFactory df = man.getOWLDataFactory();
		OWLReasonerFactory rf = new ReasonerFactory();
		OWLReasoner r = rf.createReasoner(o);
		r.precomputeInferences(InferenceType.CLASS_HIERARCHY);
		r.getSubClasses(df.getOWLClass("http://www.co−ode.org/ontologies/pizza/pizza.owl#RealItalianPizza"),false).forEach(System.out::println);

	}

}
