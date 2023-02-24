package MetaModel.constraints;

/*Generated by MPS */

import jetbrains.mps.smodel.runtime.BaseConstraintsAspectDescriptor;
import jetbrains.mps.smodel.runtime.ConstraintsDescriptor;
import org.jetbrains.mps.openapi.language.SAbstractConcept;
import jetbrains.mps.smodel.runtime.base.BaseConstraintsDescriptor;
import jetbrains.mps.lang.smodel.ConceptSwitchIndex;
import jetbrains.mps.lang.smodel.ConceptSwitchIndexBuilder;
import jetbrains.mps.smodel.adapter.ids.MetaIdFactory;

public class ConstraintsAspectDescriptor extends BaseConstraintsAspectDescriptor {
  public ConstraintsAspectDescriptor() {
  }

  @Override
  public ConstraintsDescriptor getConstraints(SAbstractConcept concept) {
    SAbstractConcept cncpt = concept;
    switch (conceptIndex.index(cncpt)) {
      case 0:
        return new AttributeConnector_Constraints();
      case 1:
        return new MapConnector_Constraints();
      case 2:
        return new PrimitiveType_Constraints();
      default:
    }
    return new BaseConstraintsDescriptor(concept);
  }
  private static final ConceptSwitchIndex conceptIndex = new ConceptSwitchIndexBuilder().put(MetaIdFactory.conceptId(0x9859e7a148764909L, 0xa6648a214c206698L, 0x5b448949fec5dbfeL), MetaIdFactory.conceptId(0x9859e7a148764909L, 0xa6648a214c206698L, 0x12a1d358b1020366L), MetaIdFactory.conceptId(0x9859e7a148764909L, 0xa6648a214c206698L, 0x355f4a6bd0ef9b28L)).seal();
}
