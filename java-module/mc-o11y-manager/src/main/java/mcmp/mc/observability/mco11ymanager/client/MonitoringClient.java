package mcmp.mc.observability.mco11ymanager.client;

import mcmp.mc.observability.mco11ymanager.common.Constants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "agent-manager", url = "${feign.agent-manager.url:}")
public interface MonitoringClient {

    @GetMapping(Constants.PREFIX_V1 + "/monitoring/plugins")
    Object getPluginList();

    @GetMapping(Constants.PREFIX_V1 + "/monitoring/target")
    Object getTargetList();
    @GetMapping(Constants.PREFIX_V1 + Constants.TARGET_PATH)
    Object getTarget(@PathVariable String nsId, @PathVariable String targetId);
    @PostMapping(Constants.PREFIX_V1 + Constants.TARGET_PATH)
    Object insertTarget(@PathVariable String nsId, @PathVariable String targetId);
    @PutMapping(Constants.PREFIX_V1 + Constants.TARGET_PATH)
    Object updateTarget(@PathVariable String nsId, @PathVariable String targetId, @RequestBody Object targetInfo);
    @DeleteMapping(Constants.PREFIX_V1 + Constants.TARGET_PATH)
    Object deleteTarget(@PathVariable String nsId, @PathVariable String targetId);


    @GetMapping(Constants.PREFIX_V1 + Constants.TARGET_ITEM_PATH)
    Object getItemList(@PathVariable String nsId, @PathVariable String targetId);
    @PostMapping(Constants.PREFIX_V1 + Constants.TARGET_ITEM_PATH)
    Object insertItem(@PathVariable String nsId, @PathVariable String targetId, @RequestBody Object monitoringConfigInfo);
    @PutMapping(Constants.PREFIX_V1 + Constants.TARGET_ITEM_PATH)
    Object updateItem(@PathVariable String nsId, @PathVariable String targetId, @RequestBody Object monitoringConfigInfo);
    @DeleteMapping(Constants.PREFIX_V1 + Constants.TARGET_ITEM_PATH + "/{itemSeq}")
    Object deleteItem(@PathVariable String nsId, @PathVariable String targetId, @PathVariable Long itemSeq);


    @GetMapping(Constants.PREFIX_V1 + Constants.TARGET_STORAGE_PATH)
    Object getStorageList(@PathVariable String nsId, @PathVariable String targetId);
    @PostMapping(Constants.PREFIX_V1 + Constants.TARGET_STORAGE_PATH)
    Object insertStorage(@PathVariable String nsId, @PathVariable String targetId, @RequestBody Object monitoringConfigInfo);
    @PutMapping(Constants.PREFIX_V1 + Constants.TARGET_STORAGE_PATH)
    Object updateStorage(@PathVariable String nsId, @PathVariable String targetId, @RequestBody Object monitoringConfigInfo);
    @DeleteMapping(Constants.PREFIX_V1 + Constants.TARGET_STORAGE_PATH + "/{storageSeq}")
    Object deleteStorage(@PathVariable String nsId, @PathVariable String targetId, @PathVariable Long storageSeq);


    @GetMapping(Constants.PREFIX_V1 + Constants.INFLUXDB_PATH)
    Object getInfluxDBList();
    @GetMapping(Constants.PREFIX_V1 + Constants.INFLUXDB_PATH + "/{influxDBSeq}/measurement")
    Object getInfluxDBFields(@PathVariable Long influxDBSeq);
    @GetMapping(Constants.PREFIX_V1 + Constants.INFLUXDB_PATH + "/{influxDBSeq}/tag")
    Object getInfluxDBTags(@PathVariable Long influxDBSeq);
    @PostMapping(Constants.PREFIX_V1 + Constants.INFLUXDB_PATH + "/{influxDBSeq}/metric")
    Object getInfluxDBMetrics(@PathVariable Long influxDBSeq, @RequestBody Object metricsInfo);
}