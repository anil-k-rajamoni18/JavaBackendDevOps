{{- define "nginx-helm.labels" -}}
  app.kubernetes.io/name: {{ .Chart.Name | quote }}
  app.kubernetes.io/version: {{ .Chart.Version | quote }}
  app.kubernetes.io/instance: {{ .Release.Name | quote }}
{{- end -}}


{{- define "nginx-helm.serviceName" -}}
{{ .Release.Name }}-service
{{- end -}}

