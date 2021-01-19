// ReactDOM.render("test", document.querySelector("#root"));
// react의 강점 : 컴포넌트

/*function Clock(props){
	//const element = (
	//	<section>
	//		<h1>America/Toronto</h1>
	//		<div>It is {new Date().toLocaleString("en-US", { timeZone: "America/Toronto" })}</div>
	//	</section>
	//); //컴포넌트
	
	//return element;
	
	let time = new Date().toLocaleString(props.locale, {timeZone:props.timeZone});
	
	// 리턴문 하나로 해결하기.. 따옴표 붙이는거 아님! 객체를 리턴
	return <section>
		   	<h1>{props.timeZone}</h1>
		   	<div>It is {time}</div>
		   </section>;
}*/

class Clock extends React.Component{
	
	//constructor(props){
	//	super(props); // props는 반드시 super로 전달해야함
	//}
	
	constructor(props){ //화면 뜨기 전 초기화 하는 곳
		super(props);
		let time = new Date().toLocaleString(this.props.locale, {timeZone:this.props.timeZone});
		let timeZone = props.timeZone;
		
		this.state = {time, timeZone};
	}
	
	tick(){
		let time = new Date().toLocaleString(this.props.locale, {timeZone:this.props.timeZone});
		let timeZone = this.props.timeZone;
		
		this.setState({time,timeZone});
	}
	
	componentDidMount(){ //화면 뜬 후 초기화 하는 곳
		//interval을 줘도 마운트는 한 번만 된다는 것을 알 수 있다.
		//console.log(this.state.timeZone + "did mount");
		setInterval(()=>{this.tick()}, 1000);
	}
	
	componentWillUnmount(){
		//console.log(this.state.timeZone + "will unmount");
	}
	
	render(){
		// let time = new Date().toLocaleString(this.props.locale, {timeZone:this.props.timeZone});
		return <section>
			   	<h1>{this.state.timeZone}</h1>
			   	<div>It is {this.state.time}</div>
			   </section>;
	}
	
}

function tick() {
	ReactDOM.render(
		<section>
			<h1>세계시간</h1>
			<Clock timeZone="Asia/Seoul" locale="ko-KR"/>
			<hr/>
			<Clock timeZone="America/Toronto" locale="en-US"/>
		</section>
		, document.querySelector("#root"));
}

/*function tick() {
	ReactDOM.render(
		<section>
			<section>
				<h1>America/Toronto</h1>
				<div>It is {new Date().toLocaleString("en-US", { timeZone: "America/Toronto" })}</div>
			</section>
			<section>
				<h1>Asia/Seoul</h1>
				<div>It is {new Date().toLocaleString("ko-KR", { timeZone: "Asia/Seoul" })}</div>
			</section>
		</section>
		, document.querySelector("#root"));
}*/

tick();
//setInterval(tick, 1000);