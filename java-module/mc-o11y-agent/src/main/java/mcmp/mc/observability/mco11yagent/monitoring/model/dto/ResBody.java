package mcmp.mc.observability.mco11yagent.monitoring.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mcmp.mc.observability.mco11yagent.monitoring.annotation.Base64EncodeField;
import mcmp.mc.observability.mco11yagent.monitoring.enums.ResultCode;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResBody<T> {
    @Builder.Default
    @JsonIgnore
    private ResultCode code = ResultCode.SUCCESS;
    @Base64EncodeField
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    private String errorMessage;

    public String getRsCode() {
        return code.getCode();
    }

    public String getRsMsg() {
        return code.getMsg();
    }
}
