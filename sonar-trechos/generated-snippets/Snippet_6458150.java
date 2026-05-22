public class Snippet__6458150 {

    public Optional<UserEntity> getUser ( final String userId )
        {
            return Optional.ofNullable ( this.userMap.get ( userId ) );
        }

}
