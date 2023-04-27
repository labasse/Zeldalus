package com.syllab.games.zeldalus.states;

import com.syllab.games.zeldalus.Character;

public interface CharacterState {
    void update(Character c, float dt);
    void processInputs(Character c);
    Object getTexture(Character c, CharacterTextureMap ctm);
}
