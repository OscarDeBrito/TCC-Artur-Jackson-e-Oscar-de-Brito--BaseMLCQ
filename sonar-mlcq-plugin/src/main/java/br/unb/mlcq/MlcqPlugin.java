package br.unb.mlcq;

import br.unb.mlcq.rules.MlcqCheckRegistrar;
import br.unb.mlcq.rules.MlcqRulesDefinition;
import org.sonar.api.Plugin;

public class MlcqPlugin implements Plugin {

    @Override
    public void define(Context context) {
        context.addExtension(MlcqRulesDefinition.class);
        context.addExtension(MlcqCheckRegistrar.class);
    }
}
