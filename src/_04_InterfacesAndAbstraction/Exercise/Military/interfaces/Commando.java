package _04_InterfacesAndAbstraction.Exercise.Military.interfaces;

import _04_InterfacesAndAbstraction.Exercise.Military.models.Mission;

import java.util.Collection;

public interface Commando {
    void addMission(Mission mission);
    Collection<Mission> getMissions();
}
