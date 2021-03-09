package se.ff.bsv;
import au.com.dius.pact.provider.junit.PactRunner;
import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit.target.HttpTarget;
import au.com.dius.pact.provider.junit.target.Target;
import au.com.dius.pact.provider.junit.target.TestTarget;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.runner.RunWith;

import java.util.Map;

@RunWith(PactRunner.class) // Say JUnit to run tests with custom Runner
@Provider("Product-service") // Set up name of tested provider
@PactFolder("../pacts") // Point where to find pacts (See also section Pacts source in documentation)

public class ProductContractTest {

}
