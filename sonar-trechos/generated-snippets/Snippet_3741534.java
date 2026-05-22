public class Snippet__3741534 {

    public void execute(@Param("dataMediaSourceId") Long dataMediaSourceId, Context context) throws Exception {
            DataMediaSource dataMediaSource = dataMediaSourceService.findById(dataMediaSourceId);

            // 查询dataSource关联的同步任务
            List<DataMedia> dataMedias = dataMediaService.listByDataMediaSourceId(dataMediaSource.getId());
            context.put("source", dataMediaSource);
            context.put("dataMedias", dataMedias);
        }

}
