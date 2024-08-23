package mcmp.mc.observability.agent.trigger.controller;

import lombok.RequiredArgsConstructor;
import mcmp.mc.observability.agent.common.Constants;
import mcmp.mc.observability.agent.common.annotation.Base64Decode;
import mcmp.mc.observability.agent.common.annotation.Base64Encode;
import mcmp.mc.observability.agent.common.model.ResBody;
import mcmp.mc.observability.agent.trigger.model.TriggerEmailUserInfo;
import mcmp.mc.observability.agent.trigger.model.TriggerSlackUserInfo;
import mcmp.mc.observability.agent.trigger.model.dto.TriggerEmailUserCreateDto;
import mcmp.mc.observability.agent.trigger.model.dto.TriggerSlackUserCreateDto;
import mcmp.mc.observability.agent.trigger.service.TriggerPolicyAlertService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(Constants.TRIGGER_URI + "/policy/{policySeq}" + "/alert")
public class TriggerPolicyAlertController {

    private final TriggerPolicyAlertService triggerPolicyAlertService;

    @Base64Encode
    @GetMapping("/slack")
    public ResBody<List<TriggerSlackUserInfo>> getSlackUserList(@PathVariable(value = "policySeq") Long policySeq) {
        ResBody<List<TriggerSlackUserInfo>> res = new ResBody<>();
        res.setData(triggerPolicyAlertService.getSlackUserList(policySeq));
        return res;
    }

    @Base64Decode(TriggerSlackUserCreateDto.class)
    @PostMapping("/slack")
    public ResBody<Void> createSlackUser(@PathVariable("policySeq") Long policySeq, @RequestBody TriggerSlackUserCreateDto dto) {
        dto.setPolicySeq(policySeq);
        return triggerPolicyAlertService.createSlackUser(dto);
    }

    @DeleteMapping("/slack/{seq}")
    public ResBody<Void> deleteSlackUser(@PathVariable("policySeq") Long policySeq, @PathVariable("seq") Long seq) {
        return triggerPolicyAlertService.deleteSlackUser(seq);
    }

    @Base64Encode
    @GetMapping("/email")
    public ResBody<List<TriggerEmailUserInfo>> getEmailUserList(@PathVariable(value = "policySeq") Long policySeq) {
        ResBody<List<TriggerEmailUserInfo>> res = new ResBody<>();
        res.setData(triggerPolicyAlertService.getEmailUserList(policySeq));
        return res;
    }

    @Base64Decode(TriggerSlackUserCreateDto.class)
    @PostMapping("/email")
    public ResBody<Void> createEmailUser(@PathVariable("policySeq") Long policySeq, @RequestBody TriggerEmailUserCreateDto dto) {
        dto.setPolicySeq(policySeq);
        return triggerPolicyAlertService.createEmailUser(dto);
    }

    @DeleteMapping("/email/{seq}")
    public ResBody<Void> deleteEmailUser(@PathVariable("policySeq") Long policySeq, @PathVariable("seq") Long seq) {
        return triggerPolicyAlertService.deleteEmailUser(seq);
    }
}
