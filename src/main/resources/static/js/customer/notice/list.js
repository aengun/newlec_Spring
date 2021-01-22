// 리엑트란 ? Flux를 구현한 프레임워크
// Flux는 뭐냐? MVC가 SNS관련 서비스를 구현하기에는 부적합하다 생각되어서
// - Store(Buffering이 가능한 Model) 개념의 단방향 렌더링(rendering) 시스템이다.
// 우리는 MVC가 지원하는 단방향 방식으로라도 코딩을 해결하면서 React가 가지는 장점을 최대한 활용할 수 있어야 한다.

// rendering -> 빠르게 렌더링 하기 위해 JSX사용
// rendering의 주체 -> state
// JSX는 기존 페이지에서 rendering을 위한 블럭을 잘라내기 한 블록 : Component (function / class:state)
// Component 중첩 -> Props

// 이벤트 value/onChange
// * fetch api
// 코드 블록 { } : JSP의 <% %>, "<%= %>", <%! %>, <%@ %> -> 두번째와 사용법이 같다.(결과물을 남기는 블록)

//==============================================================================
// NoticeList 컴포넌트

class NoticeList extends React.Component {

	constructor() {
		super();

		//this.queryInput = null;
		this.queryInput = React.createRef();

		this.state = {
			list: [
				{ "id": 92032472, "title": "eff", "writerId": "newlec", "content": "af" }
			],
			count: 0,
			//page: 1,
			//field : "title"
			//query : ""
		};

		// 화면에 출력할 데이터 : state / 아니면 일반변수
		this.page = 13;
		this.field = "title"
		this.query = "";

		let offset = (this.page - 1) % 5;
		this.startNum = this.page - offset;

		console.log("const"); //실행흐름을 보기 위한 콘솔
	}

	componentDidMount() {
		/*fetch(`/api/notice/list?p=${this.state.page}`)
			.then((response) => {
				return response.json();
			})
			.then((list) => { // list : json
				// console.log(list);
				this.setState({ list }); //== {list : list}
			});*/
		this.invalidate();

		//this.queryInput = document.querySelecgtor(".query");

		console.log("Mount"); //실행흐름을 보기 위한 콘솔
	}

	pageClickHandler(e) {
		e.preventDefault(); //콘솔 확인을 위해 이벤트 막음
		//console.log(`${e.target.innerText} page cliked`);

		/*fetch(`/api/notice/list?p=${e.target.innerText}`)
			.then((response) => {
				return response.json();
			})
			.then((list) => { // list : json
				// console.log(list);
				this.setState({ list }); //== {list : list}
			});*/

		//this.setState({page:e.target.innerText});

		this.page = e.target.innerText;
		this.invalidate();
	}

	invalidate() {
		fetch(`/api/notice/list?p=${this.page}&f=${this.field}&q=${this.query}`)
			.then((response) => {
				return response.json();
			})
			.then((data) => {
				this.setState(data);

				let offset = (this.page - 1) % 5;
				this.startNum = this.page - offset;
			});
		//.then(({list, count}) => {
		//	this.setState({list, count});
		//}); //밑과 같음
		//.then((data) => {
		//	this.setState({
		//		list: data.list,
		//		count: data.count
		//	});
		//})
	}

	searchButtonClickHandler(e) {
		e.preventDefault();

		// this.query = this.queryInput.value;
		this.query = this.queryInput.current.value;

		this.invalidate();
	}

	render() {
		console.log("render"); //실행흐름을 보기 위한 콘솔
		return <main className="main">
			<h2 className="main title">공지사항</h2>
			<div className="breadcrumb">
				<h3 className="hidden">경로</h3>
				<ul>
					<li>home</li>
					<li>고객센터</li>
					<li>공지사항</li>
				</ul>
			</div>

			<div className="search-form margin-top first align-right">
				<h3 className="hidden">공지사항 검색폼</h3>
				<form className="table-form">
					<fieldset>
						<legend className="hidden">공지사항 검색 필드</legend>
						<label className="hidden">검색분류</label>
						<select name="f">
							<option value="title">제목</option>
							<option value="writer_Id">작성자</option>
						</select>
						<label className="hidden">검색어</label>
						<input className="query" ref={this.queryInput} type="text" name="q" />
						<input className="btn btn-search" type="submit" onClick={this.searchButtonClickHandler.bind(this)} value="검색" />
					</fieldset>
				</form>
			</div>

			<div className="notice margin-top">
				<h3 className="hidden">공지사항 목록</h3>
				<table className="table">
					<thead>
						<tr>
							<th className="w60">번호</th>
							<th className="expand">제목</th>
							<th className="w100">작성자</th>
							<th className="w100">작성일</th>
							<th className="w60">조회수</th>
						</tr>
					</thead>
					<tbody>
						{
							// this.state.list.map(n => <tr key={n.id}><td colSpan="5">{n.title}</td></tr>)
							this.state.list.map(n => <tr key={n.id}>
								<td>{n.id}</td>
								<td className="title indent text-align-left"><a href="">{n.title}</a></td>
								<td>{n.writerId}</td>
								<td>{n.regdate}</td>
								<td>{n.hit}</td>
							</tr>)
						}
					</tbody>
				</table>
			</div>


			<div className="indexer margin-top align-right">
				<h3 className="hidden">현재 페이지</h3>
				<div><span className="text-orange text-strong"></span>{this.page} / pages</div>
			</div>

			<div className="margin-top align-center pager">
				<div>
					{
						this.startNum > 1
							? <a className="btn btn-prev" href="">이전</a>
							: <span className="btn btn-prev" onClick={() => { alert('이전 페이지가 없습니다.'); }}>이전</span>
					}
				</div>
				<ul className="-list- center" onClick={this.pageClickHandler.bind(this)}>
					{
						[0, 1, 2, 3, 4].map(i => <li key={this.startNum + i}><a className="" href="?p={i}&f=&q=">{this.startNum + i}</a></li>)
					}
				</ul>
				<div>

					<a className="btn btn-next" href="">다음</a>
					<span className="btn btn-next" onClick={() => { alert('다음 페이지가 없습니다.'); }}>다음</span>

				</div>

			</div>

		</main>
	}
}

ReactDOM.render(
	<NoticeList />
	, document.querySelector("#main"));