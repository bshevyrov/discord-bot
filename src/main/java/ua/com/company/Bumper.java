package ua.com.company;


import net.dv8tion.jda.api.entities.Member;
import ua.com.company.exception.BumperNotFound;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Bumper {
    //TODO when role changes than no changes in set.
    // Highest role 2. When this role take from member,
    // ENTITY still have highestRolePosition 2

    public static Set<Entity> bumpers = new LinkedHashSet<>();

    /**
     * This method add Member as Entity to LinkedHashSet of Bumpers
     *
     * @param member Discord Member
     * @return Answer add member or already added member
     */
    public static String add(Member member) {
        boolean rsl;
        rsl = bumpers.add(new Entity(member.getId(), member.getUser().getName(), getHigherRolePosition(member)));
        sort(bumpers);
        return rsl ? "Add member " + member.getUser().getName() : "Member " + member.getUser().getName() + " already added";
    }

    /**
     * @return Set of Entity;
     */
    public static Set<Entity> findAll() {
        List<String> list = bumpers.stream()
                .map(bumper -> bumper.getUsername() + " " + bumper.getBumpTime())
                .collect(Collectors.toList());
//        if (list.size() == 0) {
//            return "There is no bumpers in the list.";
//        }
//        StringBuilder resultAnswer = new StringBuilder();
//        for (int i = 0; i < list.size(); i++) {
//            resultAnswer.append(i + 1);
//            resultAnswer.append(". ");
//            resultAnswer.append(list.get(i));
//            resultAnswer.append("\n");
//        }
//        return resultAnswer.toString();
        return bumpers;
    }

    /**
     * This method search Entity in set of bumpers.
     *
     * @param id String id of Member
     * @return Entity if find it in bumpers or throw RuntimeException
     */
    public static Entity findById(String id) throws BumperNotFound {
        Optional<Entity> current = bumpers.stream()
                .filter(bumpers -> bumpers.equals(new Entity(id)))
                .findFirst();
        if (current.isPresent()) {
            return current.get();
        } else {
            throw new BumperNotFound(id);
        }
    }

    /**
     * This method check exist or not Bumper in list.
     *
     * @param id String id of Bumper.
     * @return true if bumper in exist in list.
     */
    public static boolean isExistId(String id) {
        Optional<Entity> current = bumpers.stream()
                .filter(bumpers -> bumpers.equals(new Entity(id)))
                .findFirst();
        return current.isPresent();
    }

    /**
     * This method remove Entity from bumpers list/
     *
     * @param member Guild Member
     * @return String representation of result
     */
    public static String remove(Member member) {
        boolean rsl;
        rsl = bumpers.remove(new Entity(member.getId()));
        return rsl ? "Member " + member.getUser().getName() + " removed" : member.getUser().getName() + " not added to list.";
    }


    /**
     * This method sort set by desc role position.
     *
     * @param bumpers Set of Entity.
     */
    private static void sort(Set<Entity> bumpers) {
        Bumper.bumpers = bumpers.stream()
                .sorted(Comparator.comparingInt(Entity::getHigherRoleId).reversed())
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    /**
     * This method return position of the highest role of member.
     *
     * @param member Discord member
     * @return Position of higher Role
     */
    private static int getHigherRolePosition(Member member) {
        final int[] roleId = {0};
        member.getRoles().forEach(role -> {
            if (role.getPosition() > roleId[0]) {
                roleId[0] = role.getPosition();
            }
        });
        return roleId[0];
    }


    public static class Entity {

        private final String id;
        private String username;
        private int higherRoleId;
        private ZonedDateTime bumpTime;
        private boolean alreadyBumped;

        public boolean isAlreadyBumped() {
            return alreadyBumped;
        }

        public void setAlreadyBumped(boolean alreadyBumped) {
            this.alreadyBumped = alreadyBumped;
        }

        public String getId() {
            return id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public int getHigherRoleId() {
            return higherRoleId;
        }

        public void setHigherRoleId(int higherRoleId) {
            this.higherRoleId = higherRoleId;
        }

        public ZonedDateTime getBumpTime() {
            return bumpTime;
        }

        public void setBumpTime(ZonedDateTime bumpTime) {
            this.bumpTime = bumpTime;
        }

        public Entity(String id, String username, int higherRoleId) {
            this.id = id;
            this.username = username;
            this.higherRoleId = higherRoleId;
            this.bumpTime = ZonedDateTime.now(ZoneId.of("Europe/Kiev")).minusYears(100);//Initial date 19XX year

        }

        public Entity(String id) {
            this.id = id;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Entity entity = (Entity) o;
            return Objects.equals(id, entity.id);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }

        @Override
        public String toString() {
            return "Entity{" +
                    "username='" + username + '\'' +
                    ", bumpTime=" + bumpTime +
                    '}';
        }
    }


}
