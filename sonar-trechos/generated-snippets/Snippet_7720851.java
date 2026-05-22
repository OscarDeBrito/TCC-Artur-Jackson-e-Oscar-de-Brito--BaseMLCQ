public class Snippet__7720851 {

    public String toString() {
                StringBuilder sb = new StringBuilder();
                sb.append("name: ").append(this.name);
                sb.append(", id: ").append(this.id);
                sb.append(", subnet range link: ").append(this.subnetRangeLink);
                sb.append(", resource link: ").append(this.connectedResourceLink);
                sb.append(", IP address: ").append(this.ipAddress);
                sb.append(", IP version: ").append(this.ipVersion);
                sb.append(", status: ").append(this.ipAddressStatus);

                return sb.toString();
            }

}
