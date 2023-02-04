import { useEffect, useState } from 'react'
import {
  Button,
  Select,
  TextField,
  MenuItem,
  InputLabel,
  FormControl
} from '@mui/material'
import socket from './config/socket'

function App () {
  const [actorName, setActorName] = useState('')
  const [actorType, setActorType] = useState('')
  const [message, setMessage] = useState('')
  const [messageType, setMessageType] = useState('')
  const [from, setFrom] = useState('')
  const [to, setTo] = useState('')
  const [actors, setActors] = useState([])
  const [status, setStatus] = useState([])

  useEffect(() => {
    socket.on('get_actors', (data) => {
      const auxRows = data.map((value, index) => {
        return {
          id: index,
          name: value,
          status: ''
        }
      })
      setActors(auxRows)
    })
    socket.on('update_status', (data) => {
      setStatus((current) => [...current, data])
    })
    socket.on('new_actor', (data) => {
      setActors((current) => [
        ...current,
        {
          id: current,
          name: data,
          status: ''
        }
      ])
    })
    socket.emit('send_actors')
    return () => {
      socket.off('get_actors')
      socket.off('update_status')
      socket.off('new_actor')
    }
  }, [])

  const createACtor = () => {
    socket.emit('create_actor', {
      actorName,
      actorType
    })
  }

  const sendMessage = () => {
    socket.emit('send_message', {
      message,
      messageType,
      from,
      to
    })
  }

  return (
    <div
      style={{
        display: 'flex',
        flexDirection: 'column',
        gap: '20px',
        width: '500px'
      }}
    >
      <TextField
        label='Actor name'
        value={actorName}
        onChange={(event) => setActorName(event.target.value)}
      />
      <FormControl>
        <InputLabel id='actor-type-select-label'>Actor type</InputLabel>
        <Select
          id='actor-type-select'
          labelId='actor-type-select-label'
          value={actorType}
          label='Actor type'
          onChange={(event) => setActorType(event.target.value)}
        >
          <MenuItem value={1}>HelloWorldActor</MenuItem>
          <MenuItem value={2}>InsultActor</MenuItem>
          <MenuItem value={3}>PingPongActor</MenuItem>
          <MenuItem value={4}>RingActor</MenuItem>
        </Select>
      </FormControl>
      <Button
        variant='contained'
        style={{ width: '200px' }}
        onClick={createACtor}
      >
        Create actor
      </Button>
      <TextField
        label='Message'
        value={message}
        onChange={(event) => setMessage(event.target.value)}
      />
      <FormControl>
        <InputLabel id='message-type-select-label'>Message type</InputLabel>
        <Select
          id='message-type-select'
          labelId='message-type-select-label'
          value={messageType}
          label='Message type'
          onChange={(event) => setMessageType(event.target.value)}
        >
          <MenuItem value={1}>Message</MenuItem>
          <MenuItem value={2}>AddInsultMessage</MenuItem>
          <MenuItem value={3}>GetAllInsultsMessage</MenuItem>
          <MenuItem value={4}>GetInsultMessage</MenuItem>
          <MenuItem value={5}>QuitMessage</MenuItem>
        </Select>
      </FormControl>
      <FormControl>
        <InputLabel id='from-select-label'>From</InputLabel>
        <Select
          id='from-select'
          labelId='from-select-label'
          value={from}
          label='From'
          onChange={(event) => setFrom(event.target.value)}
        >
          {actors.map((value, i) => {
            return (
              <MenuItem key={i} value={value.name}>
                {value.name}
              </MenuItem>
            )
          })}
        </Select>
      </FormControl>
      <FormControl>
        <InputLabel id='to-select-label'>To</InputLabel>
        <Select
          id='to-select'
          labelId='to-select-label'
          value={to}
          label='From'
          onChange={(event) => setTo(event.target.value)}
        >
          {actors.map((value, i) => {
            return (
              <MenuItem key={i} value={value.name}>
                {value.name}
              </MenuItem>
            )
          })}
        </Select>
      </FormControl>
      <Button
        variant='contained'
        style={{ width: '200px' }}
        onClick={sendMessage}
      >
        Send message
      </Button>
      <ul style={{ fontSize: '20px', fontFamily: 'Roboto' }}>
        {actors.map((value, i) => {
          return (
            <li key={i}>
              {value.name} :{' '}
              {status.findLast((s) => s.name === value.name) !== undefined
                ? status.findLast((s) => s.name === value.name).status
                : ''}
            </li>
          )
        })}
      </ul>
    </div>
  )
}

export default App
