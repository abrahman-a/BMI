import React,{useEffect,useState} from 'react';
import { DataGrid } from '@mui/x-data-grid';
import axios from 'axios';
import {useParams} from 'react';
import { Button } from '@mui/material';


export default function Tables() {
   
    const [data, setData] = useState([]);
    const [isDeletede, setIsDeleted] = useState(false);


    const fetchData = async () => {
        try {
          const response = await axios.get('http://localhost:8096/api/bmi-records/listUser');
          setData(response.data);
            // console

        } catch (error) {
            console.error('Error fetching data:', error);
        }
    };
    useEffect(() => {

        fetchData();
    }, []);


        const handleDelete = async (id) =>{
        try{
            await axios.delete(`http://localhost:8096/api/bmi-records/deleteData/${id}`);
            setIsDeleted(true);
             
            fetchData();
        }
        catch(error){
            console.log(error);
        }
        if (isDeletede){
            return alert("deleted");
        }
    }

  const columns = [
  { field: 'id', headerName: 'ID', width: 70 },
  { field: 'fullName', headerName: 'FullName', width: 130 },
  { field: 'gender', headerName: 'Gender', width: 130 },
  {
    field: 'age',
    headerName: 'Age',
    type: 'number',
    width: 90,
  },
  { field: 'height', headerName: 'height', width: 130 },
  { field: 'weight', headerName: 'weight', width: 130 },
  { field: 'bmiCategory', headerName: 'Status', width: 130 },

          {field: 'action', headerName : 'Action', width: 200,
      
        renderCell: (params) =>{
          return (
              <div className="cellAction" style={{}}>
                  <Button onClick={() => handleDelete(params.id)} style={{backgroundColor:"Red", color:"white", marginRight: "10px"}}  >Delete</Button>
                  <Button onClick={() => handleDelete(params.id)} style={{backgroundColor:"blue", color:"white", marginRight: "10px"}}  >Update</Button>

              </div>
            );
        }
      },
   
];

  return (
    <div style={{ height: 400, width: '100%' }}>
      <DataGrid
        rows={data}
        columns={columns}
        initialState={{
          pagination: {
            paginationModel: { page: 0, pageSize: 5 },
          },
        }}
        pageSizeOptions={[5, 10]}
        checkboxSelection
      />
    </div>
  );
}