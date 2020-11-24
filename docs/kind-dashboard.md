# Setup Dashboard UI for KIND

KIND does not have a built in Dashboard UI like minikube. But you can still setup Dashboard, a web based Kubernetes UI, to view your cluster. Follow these instructions to setup Dashboard for kind.

1. To deploy Dashboard, run the following command:

```bash
$ kubectl apply -f https://raw.githubusercontent.com/kubernetes/dashboard/v2.0.0/aio/deploy/recommended.yaml
```

2. Verify that Dashboard is deployed and running.

```bash
$ watch kubectl get pod -n kubernetes-dashboard
```

3. Create a ClusterRoleBinding to provide admin access to the newly created cluster:

```bash
$ kubectl create clusterrolebinding default-admin --clusterrole cluster-admin --serviceaccount=default:default
```

4. To login to Dashboard, you need a Bearer Token. Use the following command to store the token in a variable:

```bash
$ kubectl get secrets -o jsonpath="{.items[?(@.metadata.annotations['kubernetes\.io/service-account\.name']=='default')].data.token}" | base64 --decode | pbcopy
```

5. You can Access Dashboard using the kubectl command-line tool by running the following command:

```bash
$ kubectl proxy
```

6. Open [http://localhost:8001/api/v1/namespaces/kubernetes-dashboard/services/https:kubernetes-dashboard:/proxy/](http://localhost:8001/api/v1/namespaces/kubernetes-dashboard/services/https:kubernetes-dashboard:/proxy/) on your browser to view your deployments and services.

**Note that** You have to save your token somewhere, otherwise you have to run step number 4 everytime you need a token to login to your Dashboard.
