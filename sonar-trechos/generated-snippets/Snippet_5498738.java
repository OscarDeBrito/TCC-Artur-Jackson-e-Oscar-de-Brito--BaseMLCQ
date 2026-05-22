public class Snippet__5498738 {

    @RequestMapping(value = "/producerConnection.query", method = {RequestMethod.GET})
        @ResponseBody
        public Object producerConnection(@RequestParam String producerGroup, @RequestParam String topic) {
            ProducerConnection producerConnection = producerService.getProducerConnection(producerGroup, topic);
            producerConnection.setConnectionSet(ConnectionInfo.buildConnectionInfoHashSet(producerConnection.getConnectionSet()));
            return producerConnection;
        }

}
