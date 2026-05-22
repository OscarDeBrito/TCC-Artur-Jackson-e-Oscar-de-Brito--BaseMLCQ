public class Snippet__5331456 {

    @Override
        public ProcessGroupEntity createProcessGroup(final Revision revision, final String parentGroupId, final ProcessGroupDTO processGroupDTO) {
            final RevisionUpdate<ProcessGroupDTO> snapshot = createComponent(
                    revision,
                    processGroupDTO,
                    () -> processGroupDAO.createProcessGroup(parentGroupId, processGroupDTO),
                    processGroup -> dtoFactory.createProcessGroupDto(processGroup));

            final ProcessGroup processGroup = processGroupDAO.getProcessGroup(processGroupDTO.getId());
            final PermissionsDTO permissions = dtoFactory.createPermissionsDto(processGroup);
            final ProcessGroupStatusDTO status = dtoFactory.createConciseProcessGroupStatusDto(controllerFacade.getProcessGroupStatus(processGroup.getIdentifier()));
            final List<BulletinDTO> bulletins = dtoFactory.createBulletinDtos(bulletinRepository.findBulletinsForSource(processGroup.getIdentifier()));
            final List<BulletinEntity> bulletinEntities = bulletins.stream().map(bulletin -> entityFactory.createBulletinEntity(bulletin, permissions.getCanRead())).collect(Collectors.toList());
            return entityFactory.createProcessGroupEntity(snapshot.getComponent(), dtoFactory.createRevisionDTO(snapshot.getLastModification()), permissions, status, bulletinEntities);
        }

}
