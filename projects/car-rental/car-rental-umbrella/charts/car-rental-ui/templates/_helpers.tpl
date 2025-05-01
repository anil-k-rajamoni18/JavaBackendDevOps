{{/*
Generate fullname for car-rental-ui using only the chart name
*/}}
{{- define "car-rental-ui.fullname" -}}
{{ .Chart.Name | trunc 63 | trimSuffix "-" }}
{{- end }}