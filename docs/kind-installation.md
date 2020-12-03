# Create KIND cluster

1. Define the ingress configuration YAML file:

```yaml
apiVersion: kind.x-k8s.io/v1alpha4
kind: Cluster
nodes:
- role: control-plane
  kubeadmConfigPatches:
  - |
    kind: InitConfiguration
    nodeRegistration:
      kubeletExtraArgs:
        node-labels: "ingress-ready=true"
        authorization-mode: "AlwaysAllow"
  extraPortMappings:
  - containerPort: 30000
    hostPort: 80
    protocol: TCP
  - containerPort: 30001
    hostPort: 443
    protocol: TCP
  - containerPort: 30002
    hostPort: 15021
    protocol: TCP
```

2. Create a cluster using the configuration above:

```
$ kind create cluster --image kindest/node:v1.19.4 --config kind/kind-istio-ingress-config.yaml
```
