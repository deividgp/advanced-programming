import { useState } from 'react'
import {
  Button,
  Select,
  TextField,
  MenuItem,
  InputLabel,
  FormControl,
  Box
} from '@mui/material'
import { DataGrid } from '@mui/x-data-grid'

function App () {
  const [actorName, setActorName] = useState('')
  const [actorType, setActorType] = useState('')
  const [message, setMessage] = useState('')
  const [from, setFrom] = useState('')
  const columns = [
    {
      field: 'name',
      headerName: 'Actor name',
      width: 150
    },
    {
      field: 'status',
      headerName: 'Status',
      width: 150
    }
  ]
  const rows = [{ id: 1, name: 'Pedro', status: 'hola' }]

  const createACtor = () => {
    console.log(actorName)
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
        <InputLabel id='demo-simple-select-label'>Actor type</InputLabel>
        <Select
          id='demo-simple-select'
          labelId='demo-simple-select-label'
          value={actorType}
          label='Actor type'
          onChange={(event) => setActorType(event.target.value)}
        >
          <MenuItem value={1}>HelloWorldActor</MenuItem>
          <MenuItem value={2}>InsultActor</MenuItem>
          <MenuItem value={3}>PingPongActor</MenuItem>
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
      <TextField
        label='From'
        value={from}
        onChange={(event) => setFrom(event.target.value)}
      />
      <Button variant='contained' style={{ width: '200px' }}>
        Send message
      </Button>
      <Box sx={{ height: 400, width: '800px' }}>
        <DataGrid
          rows={rows}
          columns={columns}
          pageSize={5}
          disableSelectionOnClick
        />
      </Box>
    </div>
  )
}

export default App
