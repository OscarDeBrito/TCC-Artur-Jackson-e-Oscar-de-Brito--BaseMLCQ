package br.unb.mlcq.rules;

import org.sonar.plugins.java.api.CheckRegistrar;
import org.sonar.plugins.java.api.JavaCheck;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MlcqCheckRegistrar implements CheckRegistrar {

    @Override
    public void register(RegistrarContext registrarContext) {
        registrarContext.registerClassesForRepository(
                MlcqRulesDefinition.REPOSITORY_KEY,
                checkClasses(),
                Collections.emptyList()
        );
    }

    public static List<Class<? extends JavaCheck>> checkClasses() {
        return Arrays.asList(
                LongMethodCheck.class,
                LongMethodStatisticalCheck.class,
                DataClassCheck.class,
                FeatureEnvyCheck.class,
                BlobGodClassCheck.class
        );
    }
}
