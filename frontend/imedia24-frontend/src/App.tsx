import React, {useEffect, useState} from 'react';
import './App.css';
import {
    Paper,
    Table,
    TableBody,
    TableCell,
    TableContainer,
    TableHead,
    TableRow
} from '@mui/material';
import Axios from 'axios';
import AppService from'./App-Service';


function App() {
    let [rows, setRows] = useState([createData('', '')]);
    let [isShown, setIsShown] = useState(false);
    let [pokemonChosen, setPokemonChosen] = useState(false);
    let [pokemonName, setPokemonName] = useState('');
    let [pokemon, setPokemon] = useState({
        name: '',
        species: '',
        img: '',
        hp: '',
        attacks: '',
        defense: '',
        type: ''
    });
    let appService:AppService;
    const searchPokemon = () => {
        Axios.get(`https://pokeapi.co/api/v2/pokemon/`+pokemonName).then(
            (res) => {
                setPokemon({
                    name: pokemonName,
                    species:res.data.species.name,
                    img:res.data.sprites.front_default,
                    hp: res.data.stats[0].base_stat,
                    attacks: res.data.stats[1].base_stat,
                    defense: res.data.stats[2].base_stat,
                    type: res.data.types[0].type.name
                });
                setPokemonChosen(true);
            }
        )
    }
    const handleClick = (name:string) => {
        setPokemonName(name);
        searchPokemon();
        setIsShown(current => !current);
    };

    function createData(
        name: string,
        url: string
    ) {
        return {name, url};
    }

    function fetchPokemons() {
        Axios.get(`https://pokeapi.co/api/v2/pokemon?limit=200`)
            .then((response) => {
                setRows(response.data.results)
            });
    }
    useEffect(() => {
        fetchPokemons()
    });
    return (
        <div>
            <TableContainer component={Paper}>
                <Table sx={{minWidth: 700}} aria-label="customized table">
                    <TableHead>
                        <TableRow className="header-row">
                            <TableCell className="header-cell" align="center">Name</TableCell>
                            <TableCell className="header-cell" align="center">Url</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {isShown && <div className="popup">
                            <div className="popup-inner">
                                <div className="displayedSection">
                                    {!pokemonChosen ? (<h1>Please choose a pokemon</h1>):(
                                       <div className="pokemon-infos-container">
                                           <h1>Pokemon Name: {pokemon.name}</h1>
                                           <img src={pokemon.img}/>
                                           <h3>Species: {pokemon.species}</h3>
                                           <h3>Type: {pokemon.type}</h3>
                                           <h4>Hp: {pokemon.hp}</h4>
                                           <h4>Attack: {pokemon.attacks}</h4>
                                           <h4>Defense: {pokemon.defense}</h4>
                                       </div>
                                    )}
                                </div>
                                <div className="button-container">
                                    <button className="button-close-popup"
                                            onClick={() => setIsShown(false)}>Close
                                    </button>
                                </div>
                            </div>
                        </div>}
                        {rows.map((row) => (
                            <TableRow onClick={() => handleClick(row.name)}>
                                <TableCell align="center">{row?.name}</TableCell>
                                <TableCell align="center">{row?.url}</TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
        </div>
    );
}
export default App;
