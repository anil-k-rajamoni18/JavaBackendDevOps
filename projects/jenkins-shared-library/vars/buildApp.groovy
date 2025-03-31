def call(String version) {
    echo "Building application version ${version}"
    sh "./mvnw clean install -Dversion=${version}"
}