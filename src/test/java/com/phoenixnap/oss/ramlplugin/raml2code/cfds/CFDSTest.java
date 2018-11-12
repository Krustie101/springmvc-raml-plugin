package com.phoenixnap.oss.ramlplugin.raml2code.cfds;

import java.util.Map;

import org.junit.Test;

import com.phoenixnap.oss.ramlplugin.raml2code.data.ApiBodyMetadata;
import com.phoenixnap.oss.ramlplugin.raml2code.helpers.RamlTypeHelper;
import com.phoenixnap.oss.ramlplugin.raml2code.plugin.TestConfig;
import com.phoenixnap.oss.ramlplugin.raml2code.raml.RamlDataType;
import com.phoenixnap.oss.ramlplugin.raml2code.rules.AbstractRuleTestBase;
import com.phoenixnap.oss.ramlplugin.raml2code.rules.Spring4ControllerInterfaceRule;

/**
 * @author rahul
 * @since 0.10.6
 */
public class CFDSTest extends AbstractRuleTestBase {

	public CFDSTest() {
		TestConfig.setResourceDepthInClassNames(2);
	}

	@Test
	public void applySpring4ClientStubRule_shouldCreate_validCode() throws Exception {
		loadRaml("cfds/api.raml");
		for (RamlDataType dataType : RAML.getTypes().values()) {
			ApiBodyMetadata tempBodyMetadata = RamlTypeHelper.mapTypeToPojo(jCodeModel, RAML, dataType.getType());
		}
		
		rule = new Spring4ControllerInterfaceRule();
		rule.apply(getControllerMetadata(), jCodeModel);
		System.out.println(serializeModel(jCodeModel));
	}
}
