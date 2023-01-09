export class PokemonModel{
    name: string;
    species: string;
    img: string;
    hp: string;
    attacks: string;
    defense: string;
    type: string;

    constructor(name: string, species: string, img: string, hp: string, attacks: string, defense: string, type: string) {
        this.name = name;
        this.species = species;
        this.img = img;
        this.hp = hp;
        this.attacks = attacks;
        this.defense = defense;
        this.type = type;
    }
}