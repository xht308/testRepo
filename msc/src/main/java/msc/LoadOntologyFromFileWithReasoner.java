package msc;

import java.io.File;

import org.semanticweb.HermiT.ReasonerFactory;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.reasoner.*;

public class LoadOntologyFromFileWithReasoner {

	public static void main(String[] args) throws OWLException {
		OWLOntologyManager man = OWLManager.createOWLOntologyManager();
		File OWLFile = new File("D:\\TraceLinksFullVersion.rdf");
		OWLOntology o;
		o = man.loadOntologyFromOntologyDocument(OWLFile);
		System.out.println(o);
		OWLReasonerFactory rf = new ReasonerFactory();
		OWLReasoner r = rf.createReasoner(o);
		r.precomputeInferences(InferenceType.CLASS_HIERARCHY);
		

	}

}
