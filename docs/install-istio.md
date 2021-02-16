# Install Istio in KIND

1. Download the latest Istio version:

```
$ curl -L https://istio.io/downloadIstio | sh -
```

2. Move to `istio-<version>/bin` directory and then:

```
$ ./istioctl install -f ../../kind/install-istio.yaml
```

3. Install addons (deploy the Kiali dashboard, along with Prometheus, Grafana, and Jaeger) from `istio-<version>` directory:

```
$ kubectl apply -f samples/addons
```
