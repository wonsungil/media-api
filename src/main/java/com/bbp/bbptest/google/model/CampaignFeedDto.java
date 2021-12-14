package com.bbp.bbptest.google.model;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CampaignFeedDto {
    private String resourceName;
    private String status;
    private String feed;
    private String campaign;
    private MatchingFunction matchingFunction;

    @Getter
    @Builder
    public static class MatchingFunction {

        private List<LeftOperand> leftOperands;
        private List<RightOperand> rightOperands;
        private String operator;
        private String functionString;

        @Getter
        @Builder
        public static class LeftOperand {

            private ConstantOperand constantOperand;

            @Getter
            @Builder
            public static class ConstantOperand {
                private boolean booleanValue;
            }
        }

        @Getter
        @Builder
        public static class RightOperand {

            private ConstantOperand constantOperand;

            @Getter
            @Builder
            public static class ConstantOperand {
                private boolean booleanValue;
            }
        }
    }
}
