# Deploy Bookinfo demo

1. Go to `istio-1.8.0` directory.

2. Enable autoinject proxy sidecar:

```
$ kubectl label namespace default istio-injection=enabled
```

3. Deploy all the Bookinfo's pods:

```
$ kubectl apply -f samples/bookinfo/platform/kube/bookinfo.yaml
```

4. To confirm that the Bookinfo application is running, send a request to it by a curl command from some pod, for example from ratings:

```
$ kubectl exec "$(kubectl get pod -l app=ratings -o jsonpath='{.items[0].metadata.name}')" -c ratings -- curl productpage:9080/productpage | grep -o "<title>.*</title>"
```

5. Define the ingress gateway for the application:

```
$ kubectl apply -f samples/bookinfo/networking/bookinfo-gateway.yaml
```

6. To confirm that the Bookinfo application is accessible from outside the cluster, run the following curl command:

```
$ curl -s "http://localhost/productpage" | grep -o "<title>.*</title>"
```

# Cleanup

1. Delete the routing rules and terminate the application pods:

```
$ samples/bookinfo/platform/kube/cleanup.sh
```

2. Confirm shutdown:

```
$ kubectl get virtualservices   #-- there should be no virtual services
$ kubectl get destinationrules  #-- there should be no destination rules
$ kubectl get gateway           #-- there should be no gateway
$ kubectl get pods              #-- the Bookinfo pods should be deleted
```