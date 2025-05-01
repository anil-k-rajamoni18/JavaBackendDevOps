{{/*
Generate fullname for MySQL using only the chart name
*/}}
{{- define "mysql.fullname" -}}
{{ .Chart.Name | trunc 63 | trimSuffix "-" }}
{{- end }}