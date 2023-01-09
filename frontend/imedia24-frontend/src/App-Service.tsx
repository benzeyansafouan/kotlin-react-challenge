import Axios from 'axios';


// function createData(
//     name: string,
//     url: string
// ) {
//     return {name, url};
// }

export default class AppService {

    // fetchPokemon(): { name: string, url: string }[] {
    //     let [rows, setRows] = useState([createData('', '')]);
    //     Axios.get(`https://pokeapi.co/api/v2/pokemon?limit=200`)
    //         .then((response) => {
    //             setRows(response.data.results)
    //         });
    //     return rows;
    // }
    //
    // searchPokemon = (pokemonName: string): {
    //     name: string, species: string, img: string, hp: string,
    //     attacks: string, defense: string, type: string
    // } => {
    //     let [pokemon, setPokemon] = useState({
    //         name: '', species: '', img: '', hp: '',
    //         attacks: '', defense: '', type: ''
    //     });
    //     Axios.get(`https://pokeapi.co/api/v2/pokemon/` + pokemonName).then(
    //         (res) => {
    //             setPokemon({
    //                 name: pokemonName,
    //                 species: res.data.species.name,
    //                 img: res.data.sprites.front_default,
    //                 hp: res.data.stats[0].base_stat,
    //                 attacks: res.data.stats[1].base_stat,
    //                 defense: res.data.stats[2].base_stat,
    //                 type: res.data.types[0].type.name
    //             });
    //             return pokemon
    //         }
    //     )
    //     return pokemon
    // }
}
