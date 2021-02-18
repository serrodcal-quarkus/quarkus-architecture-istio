# Quarkus Architecture Istio

Evolution of [quarkus-architecture](https://github.com/serrodcal/quarkus-architecture) project. In this new project, the goal is to use [Istio](https://istio.io) as Service Mesh.

Replace security, monitoring or resilience features in Quarkus delegating these features to Istio. So, Quarkus will only have the business logic.

![](/img/istio.png)

The main adventage is to be able to build a native application, this is lighter artifacts, better boot time and less memory footprint.

## Requirements

* Docker 3.1.0
* Kubernetes 1.20.2
* Istio 1.9.0

## Installing

1. Go to `docs/kind-installation.md` and follow the instructions there.
2. Then, go to `docs/install-istio.md` and follow the instructions there.
3. _Optional step:_ Finally, go to `docs/deploy-bookinfo.md` to run the Bookinfo demo.

### Using addons

Open Kiali as follow once you are in `istio-1.8.0/bin` directory:

```
$ ./istioctl dashboard kiali
```

Or choose another addons from the list below:

```
Available Commands:
  controlz    Open ControlZ web UI
  envoy       Open Envoy admin web UI
  grafana     Open Grafana web UI
  jaeger      Open Jaeger web UI
  kiali       Open Kiali web UI
  prometheus  Open Prometheus web UI
  zipkin      Open Zipkin web UI
```

## Cleanup the Bookinfo demo

**Note that** this is needed only if you applied the optional step or the step 3 from `Installing` section above. 

Remove `istio-injection` label from default namespace:

```
$ kubectl label namespace default istio-injection-
```

Then, let's remove the bookinfo stack:

```
$ kubectl delete -f samples/bookinfo/platform/kube/bookinfo.yaml
```

Finally, remove the bookinfo configuration in the Istio's gateway:

```
$ kubectl delete -f samples/bookinfo/networking/bookinfo-gateway.yaml
```

## Deploy the Quarkus Archutecture application

Run the following command:

```
$ kubectl apply -f k8s
```

### Demo

![](/img/kiali.png)

## Authors

* [Serrodcal](https://github.com/serrodcal)
