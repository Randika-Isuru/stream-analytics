package org.monitoring.stream.analytics;

import java.util.HashMap;
import java.util.Map;

import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.JsonAnyGetter;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.JsonAnySetter;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "alertState", "responseWarning", "responseCritical",
		"criticalAltThreshold", "severityStatus", "severity", "reset",
		"monitorId", "monitoredCIID", "monitorName", "Product",
		"locationLabel", "type", "minion", "minionId",
		"totalRequestHeaderSize", "duration", "result",
		"totalResponseBodySize", "totalRequestBodySize",
		"totalResponseHeaderSize", "typeLabel", "location", "id", "timestamp",
		"error", "frequency", "secureCredentials" })
public class SyntheticResult {

	@JsonProperty("alertState")
	private Boolean alertState;
	@JsonProperty("productId")
	private String productId;
	@JsonProperty("responseWarning")
	private Integer responseWarning;
	@JsonProperty("responseCritical")
	private Integer responseCritical;
	@JsonProperty("criticalAltThreshold")
	private Integer criticalAltThreshold;
	@JsonProperty("severityStatus")
	private String severityStatus;
	@JsonProperty("severity")
	private Integer severity;
	@JsonProperty("reset")
	private Boolean reset;
	@JsonProperty("monitorId")
	private String monitorId;
	@JsonProperty("monitoredCIID")
	private String monitoredCIID;
	@JsonProperty("monitorName")
	private String monitorName;
	@JsonProperty("Product")
	private String product;
	@JsonProperty("locationLabel")
	private String locationLabel;
	@JsonProperty("type")
	private String type;
	@JsonProperty("minion")
	private String minion;
	@JsonProperty("minionId")
	private String minionId;
	@JsonProperty("totalRequestHeaderSize")
	private Integer totalRequestHeaderSize;
	@JsonProperty("duration")
	private Double duration;
	@JsonProperty("result")
	private String result;
	@JsonProperty("totalResponseBodySize")
	private Integer totalResponseBodySize;
	@JsonProperty("totalRequestBodySize")
	private Integer totalRequestBodySize;
	@JsonProperty("totalResponseHeaderSize")
	private Integer totalResponseHeaderSize;
	@JsonProperty("typeLabel")
	private String typeLabel;
	@JsonProperty("location")
	private String location;
	@JsonProperty("id")
	private String id;
	@JsonProperty("timestamp")
	private Integer timestamp;
	@JsonProperty("error")
	private String error;
	@JsonProperty("frequency")
	private Integer frequency;
	@JsonProperty("secureCredentials")
	private String secureCredentials;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("alertState")
	public Boolean getAlertState() {
		return alertState;
	}

	@JsonProperty("alertState")
	public void setAlertState(Boolean alertState) {
		this.alertState = alertState;
	}
	
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	@JsonProperty("responseWarning")
	public Integer getResponseWarning() {
		return responseWarning;
	}

	@JsonProperty("responseWarning")
	public void setResponseWarning(Integer responseWarning) {
		this.responseWarning = responseWarning;
	}

	@JsonProperty("responseCritical")
	public Integer getResponseCritical() {
		return responseCritical;
	}

	@JsonProperty("responseCritical")
	public void setResponseCritical(Integer responseCritical) {
		this.responseCritical = responseCritical;
	}

	@JsonProperty("criticalAltThreshold")
	public Integer getCriticalAltThreshold() {
		return criticalAltThreshold;
	}

	@JsonProperty("criticalAltThreshold")
	public void setCriticalAltThreshold(Integer criticalAltThreshold) {
		this.criticalAltThreshold = criticalAltThreshold;
	}

	@JsonProperty("severityStatus")
	public String getSeverityStatus() {
		return severityStatus;
	}

	@JsonProperty("severityStatus")
	public void setSeverityStatus(String severityStatus) {
		this.severityStatus = severityStatus;
	}

	@JsonProperty("severity")
	public Integer getSeverity() {
		return severity;
	}

	@JsonProperty("severity")
	public void setSeverity(Integer severity) {
		this.severity = severity;
	}

	@JsonProperty("reset")
	public Boolean getReset() {
		return reset;
	}

	@JsonProperty("reset")
	public void setReset(Boolean reset) {
		this.reset = reset;
	}

	@JsonProperty("monitorId")
	public String getMonitorId() {
		return monitorId;
	}

	@JsonProperty("monitorId")
	public void setMonitorId(String monitorId) {
		this.monitorId = monitorId;
	}

	@JsonProperty("monitoredCIID")
	public String getMonitoredCIID() {
		return monitoredCIID;
	}

	@JsonProperty("monitoredCIID")
	public void setMonitoredCIID(String monitoredCIID) {
		this.monitoredCIID = monitoredCIID;
	}

	@JsonProperty("monitorName")
	public String getMonitorName() {
		return monitorName;
	}

	@JsonProperty("monitorName")
	public void setMonitorName(String monitorName) {
		this.monitorName = monitorName;
	}

	@JsonProperty("Product")
	public String getProduct() {
		return product;
	}

	@JsonProperty("Product")
	public void setProduct(String product) {
		this.product = product;
	}

	@JsonProperty("locationLabel")
	public String getLocationLabel() {
		return locationLabel;
	}

	@JsonProperty("locationLabel")
	public void setLocationLabel(String locationLabel) {
		this.locationLabel = locationLabel;
	}

	@JsonProperty("type")
	public String getType() {
		return type;
	}

	@JsonProperty("type")
	public void setType(String type) {
		this.type = type;
	}

	@JsonProperty("minion")
	public String getMinion() {
		return minion;
	}

	@JsonProperty("minion")
	public void setMinion(String minion) {
		this.minion = minion;
	}

	@JsonProperty("minionId")
	public String getMinionId() {
		return minionId;
	}

	@JsonProperty("minionId")
	public void setMinionId(String minionId) {
		this.minionId = minionId;
	}

	@JsonProperty("totalRequestHeaderSize")
	public Integer getTotalRequestHeaderSize() {
		return totalRequestHeaderSize;
	}

	@JsonProperty("totalRequestHeaderSize")
	public void setTotalRequestHeaderSize(Integer totalRequestHeaderSize) {
		this.totalRequestHeaderSize = totalRequestHeaderSize;
	}

	@JsonProperty("duration")
	public Double getDuration() {
		return duration;
	}

	@JsonProperty("duration")
	public void setDuration(Double duration) {
		this.duration = duration;
	}

	@JsonProperty("result")
	public String getResult() {
		return result;
	}

	@JsonProperty("result")
	public void setResult(String result) {
		this.result = result;
	}

	@JsonProperty("totalResponseBodySize")
	public Integer getTotalResponseBodySize() {
		return totalResponseBodySize;
	}

	@JsonProperty("totalResponseBodySize")
	public void setTotalResponseBodySize(Integer totalResponseBodySize) {
		this.totalResponseBodySize = totalResponseBodySize;
	}

	@JsonProperty("totalRequestBodySize")
	public Integer getTotalRequestBodySize() {
		return totalRequestBodySize;
	}

	@JsonProperty("totalRequestBodySize")
	public void setTotalRequestBodySize(Integer totalRequestBodySize) {
		this.totalRequestBodySize = totalRequestBodySize;
	}

	@JsonProperty("totalResponseHeaderSize")
	public Integer getTotalResponseHeaderSize() {
		return totalResponseHeaderSize;
	}

	@JsonProperty("totalResponseHeaderSize")
	public void setTotalResponseHeaderSize(Integer totalResponseHeaderSize) {
		this.totalResponseHeaderSize = totalResponseHeaderSize;
	}

	@JsonProperty("typeLabel")
	public String getTypeLabel() {
		return typeLabel;
	}

	@JsonProperty("typeLabel")
	public void setTypeLabel(String typeLabel) {
		this.typeLabel = typeLabel;
	}

	@JsonProperty("location")
	public String getLocation() {
		return location;
	}

	@JsonProperty("location")
	public void setLocation(String location) {
		this.location = location;
	}

	@JsonProperty("id")
	public String getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(String id) {
		this.id = id;
	}

	@JsonProperty("timestamp")
	public Integer getTimestamp() {
		return timestamp;
	}

	@JsonProperty("timestamp")
	public void setTimestamp(Integer timestamp) {
		this.timestamp = timestamp;
	}

	@JsonProperty("error")
	public String getError() {
		return error;
	}

	@JsonProperty("error")
	public void setError(String error) {
		this.error = error;
	}

	@JsonProperty("frequency")
	public Integer getFrequency() {
		return frequency;
	}

	@JsonProperty("frequency")
	public void setFrequency(Integer frequency) {
		this.frequency = frequency;
	}

	@JsonProperty("secureCredentials")
	public String getSecureCredentials() {
		return secureCredentials;
	}

	@JsonProperty("secureCredentials")
	public void setSecureCredentials(String secureCredentials) {
		this.secureCredentials = secureCredentials;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}